package com.epam.esm.zotov.module2.dal.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Certificate {
    private long certificateId;
    private String name;
    private String description;
    private BigDecimal price;
    private short duration;
    private Instant createDate;
    private Instant lastUpdateDate;
    private List<String> tags;

    public Certificate() {
        tags = new ArrayList<String>();
    }

    public Certificate(long certificateId, String name, String description, BigDecimal price, short duration,
            Instant createDate, Instant lastUpdateDate, List<String> tags) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = tags;
    }

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (certificateId ^ (certificateId >>> 32));
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + duration;
        result = prime * result + ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Certificate other = (Certificate) obj;
        if (certificateId != other.certificateId)
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (duration != other.duration)
            return false;
        if (lastUpdateDate == null) {
            if (other.lastUpdateDate != null)
                return false;
        } else if (!lastUpdateDate.equals(other.lastUpdateDate))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Certificate [certificateId=" + certificateId + ", createDate=" + createDate + ", description="
                + description + ", duration=" + duration + ", lastUpdateDate=" + lastUpdateDate + ", name=" + name
                + ", price=" + price + ", tags=" + tags + "]";
    }
}