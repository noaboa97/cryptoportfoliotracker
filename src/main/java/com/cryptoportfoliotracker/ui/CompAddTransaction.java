
package com.cryptoportfoliotracker.ui;/*
import com.cryptoportfoliotracker.entities.Asset;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;


import java.util.List;

public class CompAddTransaction extends FormLayout {

  DateTimePicker dateTimePicker = new DateTimePicker();

  TextField SrcAsset = new TextField("SrcAsset");
  TextField lastName = new TextField("Last name");
  EmailField email = new EmailField("Email");
  ComboBox<Status> status = new ComboBox<>("Status");
  ComboBox<Company> company = new ComboBox<>("Company");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public CompAddTransaction(List<Company> companies, List<Status> statuses) {
    addClassName("contact-form");



    company.setItems(companies);
    company.setItemLabelGenerator(Company::getName);
    status.setItems(statuses);
    status.setItemLabelGenerator(Status::getName);

    add(firstName,


        lastName,
        email,
        company,
        status,
        createButtonsLayout());
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);


    close.addClickShortcut(Key.ESCAPE);

    return new HorizontalLayout(save, delete, close);


  }
}

 */