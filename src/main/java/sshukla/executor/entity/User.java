package sshukla.executor.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @author 'Seemant Shukla' on '28/10/2022'
 */

@Data
@Builder
@Entity
@Table(schema = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String gender;

}
