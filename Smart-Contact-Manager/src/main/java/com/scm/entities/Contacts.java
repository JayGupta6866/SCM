// package com.scm.entities;


// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "contact")
// public class Contacts {
//     @Id
//     private String cid;
//     //@Column(nullable = false)
//     private String name;
//     //@Column(nullable = false)
//     private String email;
//     //@Column(unique = true, nullable = false)
//     private String phoneNumber;
//     private String address;
//     private String picture;
//     @Column(length = 1000)
//     private String desc;
//     private boolean isFav = false;

//     @ManyToOne
//     private User user;
// }   

package com.scm.entities;

import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contacts {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 1000)
    private String description;
    private boolean favorite = false;
    private String websiteLink;
    private String linkedInLink;
    // private List<String> socialLinks=new ArrayList<>();

    @ManyToOne
    private User user;

}
