package raj.yash.Prospecta_Assigmnet_01.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryTO {
    private Integer id;
    private String name;
    private Boolean isActive;

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public Boolean getIsActive(){
        return this.isActive;
    }
}
