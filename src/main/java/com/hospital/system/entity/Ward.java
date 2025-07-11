package com.hospital.system.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wards")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ward_no", unique = true, nullable = false)
    private String wardNo;

    @Column(name = "total_beds", nullable = false)
    private Integer totalBeds;

    @Column(name = "used_beds", nullable = false)
    private Integer usedBeds = 0; // 提供一个默认值 0

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getWardNo() { return wardNo; }
    public void setWardNo(String wardNo) { this.wardNo = wardNo; }
    public Integer getTotalBeds() { return totalBeds; }
    public void setTotalBeds(Integer totalBeds) { this.totalBeds = totalBeds; }
    public Integer getUsedBeds() { return usedBeds; }
    public void setUsedBeds(Integer usedBeds) { this.usedBeds = usedBeds; }
}