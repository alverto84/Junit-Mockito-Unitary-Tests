package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.services.impl.RestaurantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class RestaurantServiceTest {

    private static final long RESTAURANT_ID = 1L;

    private static final Restaurant RESTAURANT = new Restaurant();
    private static final String NAME = "Hamburguesa";
    private static final String DESCRIPTION = "Tipo de hamburguesa";
    private static final String ADRESS = "C/Orfeón Santa Cecilia";
    private static final String IMAGE = "www.image.com";
    private static final List<Turn> TURN_LIST = new ArrayList<>();
    private static final List<Board> BOARD_LIST= new ArrayList<>();
    private static final List<Reservation> RESERVATION_LIST= new ArrayList<>();


    @Mock
    RestaurantRepository restaurantRepository;

    @InjectMocks
    RestaurantServiceImpl restaurantService;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        RESTAURANT.setId(RESTAURANT_ID);
        RESTAURANT.setName(NAME);
        RESTAURANT.setDescription(DESCRIPTION);
        RESTAURANT.setAddress(ADRESS);
        RESTAURANT.setImage(IMAGE);
        RESTAURANT.setTurns(TURN_LIST);
        RESTAURANT.setBoards(BOARD_LIST);
        RESTAURANT.setReservations(RESERVATION_LIST);

    }

    @Test
    public void getRestaurantByIdTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
        restaurantService.getRestaurantById(RESTAURANT_ID);
    }

    @Test(expected = BookingException.class)
    public void getRestaurantByIdTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
        restaurantService.getRestaurantById(RESTAURANT_ID);
        fail();
    }

    @Test
    public void getRestaurantsTest() throws BookingException {
        final Restaurant restaurant = new Restaurant();
        Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
        final List<RestaurantRest> response = restaurantService.getRestaurants();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }
}
