package raj.yash.Prospecta_Assigmnet_01.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import raj.yash.Prospecta_Assigmnet_01.Constant.TABLE;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = TABLE.CATEGORY)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(name="is_active")
    private Boolean isActive;
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    @PrePersist
    protected void onCreate() {
        if (createTime == null) {
            createTime = LocalDateTime.now(); // Set current time if not already set
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

}
