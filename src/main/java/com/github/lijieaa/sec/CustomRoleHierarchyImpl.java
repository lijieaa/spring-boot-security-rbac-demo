package com.github.lijieaa.sec;

import com.github.lijieaa.entity.Hierarchy;
import com.github.lijieaa.service.impl.HierarcyServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.hierarchicalroles.CycleInRoleHierarchyException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/6.
 */
public class CustomRoleHierarchyImpl implements RoleHierarchy {

    private static final Log logger = LogFactory.getLog(CustomRoleHierarchyImpl.class);

    private String roleHierarchyStringRepresentation = null;

    @Autowired
    HierarcyServiceImpl hierarcyService;

    /**
     * rolesReachableInOneStepMap is a Map that under the key of a specific role name
     * contains a set of all roles reachable from this role in 1 step
     */
    private Map<GrantedAuthority, Set<GrantedAuthority>> rolesReachableInOneStepMap = null;

    /**
     * rolesReachableInOneOrMoreStepsMap is a Map that under the key of a specific role
     * name contains a set of all roles reachable from this role in 1 or more steps
     */
    private Map<GrantedAuthority, Set<GrantedAuthority>> rolesReachableInOneOrMoreStepsMap = null;


    public Collection<GrantedAuthority> getReachableGrantedAuthorities(
            Collection<? extends GrantedAuthority> authorities) {


        Iterable<Hierarchy> hierarchies=hierarcyService.findAll();

        StringBuffer buffer=new StringBuffer();

        for (Hierarchy hierarchy : hierarchies) {
            buffer.append(hierarchy.getHierarchyContent()+" ");
        }


        this.roleHierarchyStringRepresentation = buffer.toString();

        logger.debug("setHierarchy() - The following role hierarchy was set: "
                + roleHierarchyStringRepresentation);

        buildRolesReachableInOneStepMap();

        buildRolesReachableInOneOrMoreStepsMap();


        if (authorities == null || authorities.isEmpty()) {
            return AuthorityUtils.NO_AUTHORITIES;
        }

        Set<GrantedAuthority> reachableRoles = new HashSet<GrantedAuthority>();

        for (GrantedAuthority authority : authorities) {
            addReachableRoles(reachableRoles, authority);
            Set<GrantedAuthority> additionalReachableRoles = getRolesReachableInOneOrMoreSteps(
                    authority);
            if (additionalReachableRoles != null) {
                reachableRoles.addAll(additionalReachableRoles);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getReachableGrantedAuthorities() - From the roles "
                    + authorities + " one can reach " + reachableRoles
                    + " in zero or more steps.");
        }

        List<GrantedAuthority> reachableRoleList = new ArrayList<GrantedAuthority>(
                reachableRoles.size());
        reachableRoleList.addAll(reachableRoles);

        return reachableRoleList;
    }

    // SEC-863
    private void addReachableRoles(Set<GrantedAuthority> reachableRoles,
                                   GrantedAuthority authority) {

        for (GrantedAuthority testAuthority : reachableRoles) {
            String testKey = testAuthority.getAuthority();
            if ((testKey != null) && (testKey.equals(authority.getAuthority()))) {
                return;
            }
        }
        reachableRoles.add(authority);
    }

    // SEC-863
    private Set<GrantedAuthority> getRolesReachableInOneOrMoreSteps(
            GrantedAuthority authority) {

        if (authority.getAuthority() == null) {
            return null;
        }

        for (GrantedAuthority testAuthority : this.rolesReachableInOneOrMoreStepsMap
                .keySet()) {
            String testKey = testAuthority.getAuthority();
            if ((testKey != null) && (testKey.equals(authority.getAuthority()))) {
                return this.rolesReachableInOneOrMoreStepsMap.get(testAuthority);
            }
        }

        return null;
    }

    /**
     * Parse input and build the map for the roles reachable in one step: the higher role
     * will become a key that references a set of the reachable lower roles.
     */
    private void buildRolesReachableInOneStepMap() {
        Pattern pattern = Pattern.compile("(\\s*([^\\s>]+)\\s*>\\s*([^\\s>]+))");

        Matcher roleHierarchyMatcher = pattern
                .matcher(this.roleHierarchyStringRepresentation);
        this.rolesReachableInOneStepMap = new HashMap<GrantedAuthority, Set<GrantedAuthority>>();

        while (roleHierarchyMatcher.find()) {
            GrantedAuthority higherRole = new SimpleGrantedAuthority(
                    roleHierarchyMatcher.group(2));
            GrantedAuthority lowerRole = new SimpleGrantedAuthority(
                    roleHierarchyMatcher.group(3));
            Set<GrantedAuthority> rolesReachableInOneStepSet;

            if (!this.rolesReachableInOneStepMap.containsKey(higherRole)) {
                rolesReachableInOneStepSet = new HashSet<GrantedAuthority>();
                this.rolesReachableInOneStepMap.put(higherRole,
                        rolesReachableInOneStepSet);
            }
            else {
                rolesReachableInOneStepSet = this.rolesReachableInOneStepMap
                        .get(higherRole);
            }
            addReachableRoles(rolesReachableInOneStepSet, lowerRole);

            logger.debug("buildRolesReachableInOneStepMap() - From role " + higherRole
                    + " one can reach role " + lowerRole + " in one step.");
        }
    }

    /**
     * For every higher role from rolesReachableInOneStepMap store all roles that are
     * reachable from it in the map of roles reachable in one or more steps. (Or throw a
     * CycleInRoleHierarchyException if a cycle in the role hierarchy definition is
     * detected)
     */
    private void buildRolesReachableInOneOrMoreStepsMap() {
        this.rolesReachableInOneOrMoreStepsMap = new HashMap<GrantedAuthority, Set<GrantedAuthority>>();
        // iterate over all higher roles from rolesReachableInOneStepMap

        for (GrantedAuthority role : this.rolesReachableInOneStepMap.keySet()) {
            Set<GrantedAuthority> rolesToVisitSet = new HashSet<GrantedAuthority>();

            if (this.rolesReachableInOneStepMap.containsKey(role)) {
                rolesToVisitSet.addAll(this.rolesReachableInOneStepMap.get(role));
            }

            Set<GrantedAuthority> visitedRolesSet = new HashSet<GrantedAuthority>();

            while (!rolesToVisitSet.isEmpty()) {
                // take a role from the rolesToVisit set
                GrantedAuthority aRole = rolesToVisitSet.iterator().next();
                rolesToVisitSet.remove(aRole);
                addReachableRoles(visitedRolesSet, aRole);
                if (this.rolesReachableInOneStepMap.containsKey(aRole)) {
                    Set<GrantedAuthority> newReachableRoles = this.rolesReachableInOneStepMap
                            .get(aRole);

                    // definition of a cycle: you can reach the role you are starting from
                    if (rolesToVisitSet.contains(role)
                            || visitedRolesSet.contains(role)) {
                        throw new CycleInRoleHierarchyException();
                    }
                    else {
                        // no cycle
                        rolesToVisitSet.addAll(newReachableRoles);
                    }
                }
            }
            this.rolesReachableInOneOrMoreStepsMap.put(role, visitedRolesSet);

            logger.debug("buildRolesReachableInOneOrMoreStepsMap() - From role " + role
                    + " one can reach " + visitedRolesSet + " in one or more steps.");
        }

    }

}
