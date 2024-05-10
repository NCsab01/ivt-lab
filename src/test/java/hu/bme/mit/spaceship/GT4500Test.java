package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryStoreMock = mock(TorpedoStore.class);
  private TorpedoStore secondaryStoreMock = mock(TorpedoStore.class);

  @BeforeEach
  public void init(){
    this.ship = new GT4500();
    this.ship.setPrimaryTorpedoStore(primaryStoreMock);
    this.ship.setSecondaryTorpedoStore(secondaryStoreMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryStoreMock.fire(1)).thenReturn(true);
    when(primaryStoreMock.isEmpty()).thenReturn(false);
    when(primaryStoreMock.getTorpedoCount()).thenReturn(1);

    when(secondaryStoreMock.fire(1)).thenReturn(true);
    when(secondaryStoreMock.isEmpty()).thenReturn(false);
    when(secondaryStoreMock.getTorpedoCount()).thenReturn(1);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(primaryStoreMock, times(1)).fire(1);
    verify(primaryStoreMock, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryStoreMock.fire(1)).thenReturn(true);
    when(primaryStoreMock.isEmpty()).thenReturn(false);
    when(primaryStoreMock.getTorpedoCount()).thenReturn(1);

    when(secondaryStoreMock.fire(1)).thenReturn(true);
    when(secondaryStoreMock.isEmpty()).thenReturn(false);
    when(secondaryStoreMock.getTorpedoCount()).thenReturn(1);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(primaryStoreMock, times(1)).fire(1);
    verify(primaryStoreMock, times(1)).getTorpedoCount();

    verify(secondaryStoreMock, times(1)).fire(1);
    verify(secondaryStoreMock, times(1)).getTorpedoCount();
  }

  @Test
  public void primaryTorpedoStore_Is_Empty(){
    // Arrange
    when(primaryStoreMock.fire(1)).thenReturn(true);
    when(primaryStoreMock.isEmpty()).thenReturn(true);
    when(primaryStoreMock.getTorpedoCount()).thenReturn(0);

    when(secondaryStoreMock.fire(1)).thenReturn(true);
    when(secondaryStoreMock.isEmpty()).thenReturn(false);
    when(secondaryStoreMock.getTorpedoCount()).thenReturn(1);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(primaryStoreMock, times(1)).isEmpty();

    verify(secondaryStoreMock, times(1)).isEmpty();
    verify(secondaryStoreMock, times(1)).fire(1);
  }

}
