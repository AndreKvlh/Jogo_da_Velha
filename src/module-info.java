/**
 * 
 */
/**
 * 
 */
module Jogo_da_Velha {
	requires javafx.controls;
    requires javafx.fxml;
    
    opens main.gui to javafx.graphics, javafx.fxml;
}