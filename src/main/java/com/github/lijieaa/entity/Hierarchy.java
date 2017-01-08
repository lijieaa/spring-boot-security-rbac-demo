package com.github.lijieaa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Hierarchy extends Base {

    @Basic
    @Column(name = "hierarchy_content", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getHierarchyContent() {
        return hierarchyContent;
    }

    public void setHierarchyContent(String hierarchyContent) {
        this.hierarchyContent = hierarchyContent;
    }

    private String hierarchyContent;
}
