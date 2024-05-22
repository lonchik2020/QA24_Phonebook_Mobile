package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Contact {
    private String name;
    private String lastNme;
    private String email;
    private String phone;
    private String address;
    private String description;

}
