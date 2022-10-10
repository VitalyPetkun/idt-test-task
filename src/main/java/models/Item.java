package models;

import lombok.Data;

@Data
public class Item {

    private Owner owner;
    private String is_accepted;
    private String score;
    private String last_activity_date;
    private String creation_date;
    private String answer_id;
    private String question_id;
    private String content_license;
}
