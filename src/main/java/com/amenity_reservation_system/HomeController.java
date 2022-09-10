package com.amenity_reservation_system;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.amenity_reservation_system.model.Reservation;
import com.amenity_reservation_system.model.User;
import com.amenity_reservation_system.service.ReservationService;
import com.amenity_reservation_system.service.UserService;


@Controller
public class HomeController {

    final UserService userService;
    final ReservationService reservationService;

    public HomeController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/reservations")
    public String reservations(Model model, HttpSession session) {
        User user = userService.get(10000L);
        session.setAttribute("user", user);
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
    
        return "reservations";
    }

    @PostMapping("/reservations-submit")
    public String reservationSubmit(@ModelAttribute Reservation reservation, 
                                    @SessionAttribute("user") User user){
        //Save to DB after updating
        assert user != null;
        reservation.setUser(user);
        reservationService.create(reservation);
        Set<Reservation> userReservations = user.getUserReservations();
        userReservations.add(reservation);
        user.setUserReservations(userReservations);
        userService.update(user.getId(), user);
        
        return "redirect:/reservations";

    }
}
