package command.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee",
        catalog = "rest")
public class Employee implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "idemployee", nullable = false, unique = true)
    private String idemployee;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    private String position;

    protected Employee(){}

    public Employee(String idemployee, String name, String position){
        this.idemployee = idemployee;
        this.name = name;
        this.position = position;
    }

    public String getIdemployee(){
        return idemployee;
    }

    public void setIdemployee(String idemployee){
        this.idemployee = idemployee;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }
}
