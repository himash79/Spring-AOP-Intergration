package lk.himash.springAOP.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "laptop")
@Entity
public class Laptop {

    @Id
    @Column(name = "Laptop_ID")
    private String lapId;

    @Column(name = "Laptop_Brand")
    private String lapBrand;

    @Column(name = "Laptop_Model")
    private String lapModel;

    @Column(name = "Laptop_Cost")
    private String lapCost;

}
