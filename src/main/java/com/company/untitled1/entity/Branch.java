package com.company.untitled1.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.MetadataTools;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@JmixEntity
@Table(name = "BRANCH", indexes = {
        @Index(name = "IDX_BRANCH_PARENT_BRANCH", columnList = "PARENT_BRANCH"),
        @Index(name = "IDX_BRANCH_TRADE_BY", columnList = "TRADE_BY"),
        @Index(name = "IDX_BRANCH_", columnList = "")
})
@Entity
public class Branch {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "BANK_CODE", nullable = false)
    @NotNull
    private String bankCode;

    @Column(name = "BRANCH_TYPE", nullable = false, length = 2)
    @NotNull
    private String branchType;

    @Column(name = "BRANCH_CODE", nullable = false)
    @NotNull
    @Id
    private String branchCode;

    @Column(name = "BRANCH_NAME", nullable = false)
    @NotNull
    private String branchName;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "PARENT_BRANCH")
    @ManyToOne(fetch = FetchType.LAZY)
    private Branch parentBranch;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "SECTOR_CODE")
    @ManyToOne(fetch = FetchType.LAZY)
    private GdSector sectorCode;

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "TAX_NO")
    private String taxNo;

    @Column(name = "CITAD_CODE")
    private String citadCode;

    @NotNull
    @Column(name = "IS_DIRECT", nullable = false, length = 2)
    private String isDirect;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "TRADE_BY")
    @ManyToOne(fetch = FetchType.LAZY)
    private Branch tradeBy;

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private String status;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private OffsetDateTime lastModifiedDate;

    @Column(name = "DELETED", length = 1)
    private String deleted;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    public void setSectorCode(GdSector sectorCode) {
        this.sectorCode = sectorCode;
    }

    public GdSector getSectorCode() {
        return sectorCode;
    }

    public void setBankCode(ListBankFake bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.getId();
    }

    public ListBankFake getBankCode() {
        return bankCode == null ? null : ListBankFake.fromId(bankCode);
    }

    public ActiveStatus getStatus() {
        return status == null ? null : ActiveStatus.fromId(status);
    }

    public void setStatus(ActiveStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public Branch getTradeBy() {
        return tradeBy;
    }

    public void setTradeBy(Branch tradeBy) {
        this.tradeBy = tradeBy;
    }

    public DirectMenber getIsDirect() {
        return isDirect == null ? null : DirectMenber.fromId(isDirect);
    }

    public void setIsDirect(DirectMenber isDirect) {
        this.isDirect = isDirect == null ? null : isDirect.getId();
    }

    public String getCitadCode() {
        return citadCode;
    }

    public void setCitadCode(String citadCode) {
        this.citadCode = citadCode;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCityCode() {
        return cityCode == null ? null : City.fromId(cityCode);
    }

    public void setCityCode(City cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.getId();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public BranchType getBranchType() {
        return branchType == null ? null : BranchType.fromId(branchType);
    }

    public void setBranchType(BranchType branchType) {
        this.branchType = branchType == null ? null : branchType.getId();
    }

    public Branch getParentBranch() {
        return parentBranch;
    }

    public void setParentBranch(Branch parentBranch) {
        this.parentBranch = parentBranch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public OffsetDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(OffsetDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public OffsetDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @InstanceName
    @DependsOnProperties({"branchCode", "branchName"})
    public String getInstanceName(MetadataTools metadataTools) {
        return String.format("%s %s %s",
                metadataTools.format(branchCode),
                " - ",
                metadataTools.format(branchName));
    }
}