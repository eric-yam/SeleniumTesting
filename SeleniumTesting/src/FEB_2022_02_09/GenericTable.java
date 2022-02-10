package FEB_2022_02_09;

import java.util.List;

import org.openqa.selenium.WebDriver;

public interface GenericTable {
	
    GenericTable withDriver(WebDriver driver);
    GenericTable withExtraConfig();
    List<String> getHeaders();
    TableData getHeadersRow();
    TableData getRow(int index);
    List<TableData> getRows();

}
