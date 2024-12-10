package raj.yash.Prospecta_Assigmnet_01.DTO;
public class ErrorResponse {
    private int statusCode;
    private String route;
    private String id;
    private String category;
    private String message;

    public ErrorResponse(int statusCode, String route, String id, String category, String message) {
        this.statusCode = statusCode;
        this.route = route;
        this.id = id;
        this.category = category;
        this.message = message;
    }

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
