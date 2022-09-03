
package com.cryptoportfoliotracker.ui;
import com.cryptoportfoliotracker.entities.CryptoAsset;
import com.cryptoportfoliotracker.entities.Platform;
import com.cryptoportfoliotracker.entities.Transaction;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import java.util.List;

public class CompAddTransaction extends FormLayout {
  Transaction transaction;
  DateTimePicker dateAndTime = new DateTimePicker();
  ComboBox<CryptoAsset> srcAsset = new ComboBox<>("Source Asset");
  ComboBox<CryptoAsset> destAsset = new ComboBox<>("Destination Asset");
  BigDecimalField srcAmount = new BigDecimalField("Source Amount");
  BigDecimalField destAmount = new BigDecimalField("Destination Amount");
  ComboBox<Platform> srcPlatform = new ComboBox<>("Source Platform");
  ComboBox<Platform> destPlatform = new ComboBox<>("Destination Platform");
  BigDecimalField fee = new BigDecimalField("Fee");
  ComboBox<CryptoAsset> feeAsset = new ComboBox<>("Fee Asset");
  TextField notes = new TextField("Notes");
  Binder<Transaction> binder = new BeanValidationBinder<>(Transaction.class);
  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public CompAddTransaction(List<CryptoAsset> CryptoAssetList, List<Platform> PlatformList) {
    addClassName("transaction-list");
    binder.bindInstanceFields(this);

    // laden der Listen für die Dropdowns
    srcAsset.setItems(CryptoAssetList);
    destAsset.setItems(CryptoAssetList);
    srcPlatform.setItems(PlatformList);
    destPlatform.setItems(PlatformList);
    feeAsset.setItems(CryptoAssetList);

    // Anzeigename defineieren welche in der Dropdown angezeigt werden
    srcAsset.setItemLabelGenerator(CryptoAsset::getShortname);
    destAsset.setItemLabelGenerator(CryptoAsset::getShortname);
    srcPlatform.setItemLabelGenerator(Platform::getName);
    destPlatform.setItemLabelGenerator(Platform::getName);
    destPlatform.setItemLabelGenerator(Platform::getName);
    feeAsset.setItemLabelGenerator(CryptoAsset::getShortname);

    add(dateAndTime,
        srcAmount,
        srcAsset,
        srcPlatform,
        destAmount,
        destAsset,
        destPlatform,
        fee,
        feeAsset,
        notes,
        createButtonsLayout());
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);


    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, transaction)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));
    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close);

  }
  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
    binder.readBean(transaction);
  }

  private void validateAndSave() {
    try {

      binder.writeBean(transaction);
      System.out.println("CompAddTransaction validateAndSave");
      transaction.getTransaction();

      fireEvent(new SaveEvent(this, transaction));

    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  // Events
  public static abstract class CompAddTransactionEvent extends ComponentEvent<CompAddTransaction> {
    private Transaction transaction;

    protected CompAddTransactionEvent(CompAddTransaction source, Transaction transaction) {
      super(source, false);
      this.transaction = transaction;
    }
    public Transaction getTransaction() {
      return transaction;
    }
  }

  public static class SaveEvent extends CompAddTransactionEvent {
    SaveEvent(CompAddTransaction source, Transaction transaction) {
      super(source, transaction);
    }
  }

  public static class DeleteEvent extends CompAddTransactionEvent {
    DeleteEvent(CompAddTransaction source, Transaction transaction) {
      super(source, transaction);
    }

  }

  public static class CloseEvent extends CompAddTransactionEvent {
    CloseEvent(CompAddTransaction source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {


    return getEventBus().addListener(eventType, listener);
  }


}