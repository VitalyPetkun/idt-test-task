package models;

import lombok.Data;

import java.util.List;

@Data
public class Answer {

    private List<Item> items;
    private String has_more;
    private String backoff;
    private String quota_max;
    private String quota_remaining;

}
