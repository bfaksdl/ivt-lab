package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mPrimTS;
  private TorpedoStore mSecTS;

  @Before
  public void init(){
    mPrimTS = mock(TorpedoStore.class);
    mSecTS  = mock(TorpedoStore.class);
    this.ship = new GT4500(mPrimTS, mSecTS);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
    when(mPrimTS.isEmpty()).thenReturn(false);
    when(mPrimTS.fire(1)).thenReturn(true);
    when(mSecTS.isEmpty()).thenReturn(false);
    when(mSecTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(mPrimTS, times(1)).isEmpty();
    verify(mPrimTS, times(1)).fire(1);
    verify(mSecTS, times(0)).isEmpty();
    verify(mSecTS, times(0)).fire(1);

    //assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_Single_FirstEmpty(){
    // Arrange
    when(mPrimTS.isEmpty()).thenReturn(true);
    when(mPrimTS.fire(1)).thenReturn(true);
    when(mSecTS.isEmpty()).thenReturn(false);
    when(mSecTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(mPrimTS, times(1)).isEmpty();
    verify(mPrimTS, times(0)).fire(1);
    verify(mSecTS, times(1)).isEmpty();
    verify(mSecTS, times(1)).fire(1);

    //assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_Single_FirstWasFiredLast(){
    // Arrange
    when(mPrimTS.isEmpty()).thenReturn(false);
    when(mPrimTS.fire(1)).thenReturn(true);
    when(mSecTS.isEmpty()).thenReturn(false);
    when(mSecTS.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedos(FiringMode.SINGLE);
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(mPrimTS, times(1)).isEmpty();
    verify(mPrimTS, times(1)).fire(1);
    verify(mSecTS, times(1)).isEmpty();
    verify(mSecTS, times(1)).fire(1);

    //assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_Single_FirstWasFiredLast_SecondaryEmtpy(){
    // Arrange
    when(mPrimTS.isEmpty()).thenReturn(false);
    when(mPrimTS.fire(1)).thenReturn(true);
    when(mSecTS.isEmpty()).thenReturn(true);
    when(mSecTS.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedos(FiringMode.SINGLE);
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    verify(mPrimTS, times(2)).isEmpty();
    verify(mPrimTS, times(2)).fire(1);
    verify(mSecTS, times(1)).isEmpty();
    verify(mSecTS, times(0)).fire(1);

    //assertEquals(true, result);
  }


  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
    when(mPrimTS.isEmpty()).thenReturn(false);
    when(mPrimTS.fire(1)).thenReturn(true);
    when(mSecTS.isEmpty()).thenReturn(false);
    when(mSecTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(mPrimTS, times(1)).isEmpty();
    verify(mPrimTS, times(1)).fire(1);
    verify(mSecTS, times(1)).isEmpty();
    verify(mSecTS, times(1)).fire(1);
    //assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_PrimaryEmtpy(){
    // Arrange
    when(mPrimTS.isEmpty()).thenReturn(true);
    when(mPrimTS.fire(1)).thenReturn(true);
    when(mSecTS.isEmpty()).thenReturn(false);
    when(mSecTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(mPrimTS, times(1)).isEmpty();
    verify(mPrimTS, times(0)).fire(1);
    verify(mSecTS, times(1)).isEmpty();
    verify(mSecTS, times(1)).fire(1);
    //assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_SecondaryEmpty(){
    // Arrange
    when(mPrimTS.isEmpty()).thenReturn(false);
    when(mPrimTS.fire(1)).thenReturn(true);
    when(mSecTS.isEmpty()).thenReturn(true);
    when(mSecTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    verify(mPrimTS, times(1)).isEmpty();
    verify(mPrimTS, times(1)).fire(1);
    verify(mSecTS, times(1)).isEmpty();
    verify(mSecTS, times(0)).fire(1);
    //assertEquals(true, result);
  }
}
