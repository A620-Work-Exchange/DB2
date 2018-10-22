package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;


    @OneToOne
    private User user;



    public Bill() {
    }


}
