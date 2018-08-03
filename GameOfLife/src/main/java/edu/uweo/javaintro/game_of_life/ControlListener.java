package edu.uweo.javaintro.game_of_life;

import java.awt.event.ActionEvent;

public interface ControlListener 
{
	public void controlActivated( ControlEvent evt );
	public void sliderAdjusted( ControlEvent evt );
}
