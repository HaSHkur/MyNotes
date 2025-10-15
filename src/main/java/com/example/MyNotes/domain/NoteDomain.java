package com.example.MyNotes.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDomain {
    public String title;
    public String description;
    public Date deadline;
}
