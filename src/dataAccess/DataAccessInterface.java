package dataAccess;

import java.util.Date;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import domain.AbstractUser;
import domain.AbstractUser.Role;
import domain.Offer;
import domain.RuralHouse;
import exceptions.AuthException;
import exceptions.DuplicatedEntityException;
import exceptions.OverlappingOfferException;

public interface DataAccessInterface {

	void initializeDB();

	Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price);

	RuralHouse createRuralHouse(String description, String city) throws DuplicatedEntityException;

	AbstractUser createUser(String email, String username, String password, Role role) throws DuplicatedEntityException;

	boolean validDni(String dni);

	Role getRole(String username);

	boolean existsUser(String username);

	boolean existsEmail(String email);

	boolean existsRuralHouse(String description, String city);

	void login(String username, String password) throws AuthException, AccountNotFoundException;

	List<RuralHouse> getAllRuralHouses();

	List<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay);

	boolean existsOverlappingOffer(RuralHouse rh, Date firstDay, Date lastDay) throws OverlappingOfferException;

}