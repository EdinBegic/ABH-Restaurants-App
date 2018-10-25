package atlantbh.restaurants.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "`user`") // user is a reserved keyword in PostgreSQL. Hibernate specific solution is to use backticks
public class User extends BaseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private Role roleName;
    private Location location;

    public User(String firstName, String lastName, String email, String phoneNumber, String passwordHash, Role roleName, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.roleName = roleName;
        this.location = location;
    }

    protected User() {
    }

    @NotBlank(message = "First name cannot be null or whitespace")
    @Size(max = 50, message = "First name cannot be longer than 50 characters")
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank(message = "Last name cannot be null or whitespace")
    @Size(max = 50, message = "Last name cannot be longer than 50 characters")
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotBlank(message = "Email cannot be null or whitespace")
    @Size(max = 100, message = "Email cannot be longer than 100 characters")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Phone number cannot be null or whitespace")
    @Size(max = 20, message = "Phone number cannot be longer than 20 characters")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotBlank(message = "Password cannot be null or whitespace")
    @Size(max = 255, message = "Hashed password cannot be longer than 255 characters")
    @Column(name = "password_hash")
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "role_name")
    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
