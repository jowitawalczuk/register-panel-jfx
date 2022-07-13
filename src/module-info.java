module register.panel.jfx {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.excelant;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.ooxml.schemas;
    opens sample;
}