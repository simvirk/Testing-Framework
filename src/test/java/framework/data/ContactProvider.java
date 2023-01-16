package framework.data;

import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

public class ContactProvider {
    @DataProvider
    public Iterator<Object[]> contactProvider(){
        List<Object[]> contact = ExcelDataReader.getDataFromSheet("Contact", false);
        return contact.iterator();
    }

    @DataProvider
    public Iterator<Object[]> guestProvider(){
        List<Object[]> contacts = ExcelDataReader.getDataFromSheet( "Guest",false);
        return contacts.iterator();
    }
}
