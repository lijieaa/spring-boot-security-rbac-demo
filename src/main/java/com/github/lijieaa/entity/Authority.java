package com.github.lijieaa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by 95205 on 2017/1/5.
 */
@Entity
public class Authority extends Base{
    private String rolId;
    private String authorityName;
    private String authorityDescription;



    @Basic
    @Column(name = "authority_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "authority_description", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getAuthorityDescription() {
        return authorityDescription;
    }

    public void setAuthorityDescription(String authorityDescription) {
        this.authorityDescription = authorityDescription;
    }

}
