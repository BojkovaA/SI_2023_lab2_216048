import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<User> createList(User elems) {
        return new ArrayList<User>(Arrays.asList(elems));
    }

    @Test
    void EveryBranch() {
        RuntimeException ex;

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(new User("Ana", "ANABANANA", null), null));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));


        User user1 = new User( null, "LUBENICKA*", "ana1@gmail.com");
        SILab2.function(user1, createList(new User ("ana1@gmail.com", "LUBENICKA*", "ana1@gmail.com")));
        assertEquals(user1.getEmail(), user1.getUsername()  );

        User user2 = new User("Stefan" , "STEFANATOR*", "stefangmail.com");
        SILab2.function(user2, createList(new User("Stefan" , "STEFANATOR*", "stefangmail.com")));
        assertFalse(user2.getEmail().contains("@") && user2.getEmail().contains("."));
        assertTrue(user2.getPassword().toLowerCase().contains(user2.getUsername().toLowerCase()) || user2.getPassword().length()<8);



        User user3 = new User("Angela" , "ANA BANANA", "angela@gmail.com");
        SILab2.function(user3, createList(new User("Angela" , "ANA BANANA", "angela@gmail.com")));
        assertTrue(user3.getPassword().contains(" "));

        User user4 = new User("Angela" , "ANABANANA", "angela@gmail.com");
        boolean rez = SILab2.function(user4, createList(new User("Angela" , "ANABANANA", "angela@gmail.com")));
        assertFalse(rez);

       String specialCharacters = "!#$%&'()*+,-./:;<=>?@[]^_`{|}";
        int spec_cnt=0;

        for (int i = 0; i < specialCharacters.length(); i++) {
            if (user4.getPassword().contains(String.valueOf(specialCharacters.charAt(i)))) {
                spec_cnt++;
            }
        }
        assertEquals(0, spec_cnt);
    }

    @Test
    void MultipleCondition(){
        RuntimeException ex;

        // if (user==null || user.getPassword()==null || user.getEmail()==null){
        //T X X
        //F T X
        //F F T
        //F F F

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(null , createList(new User ("ana1@gmail.com", "LUBENICKA*", "ana1@gmail.com"))));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(new User("Ana", null, "ana@gmail.com"), createList(new User ("ana1@gmail.com", "LUBENICKA*", "ana1@gmail.com"))));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(new User("Ana", "Ana", null), createList(new User ("ana1@gmail.com", "LUBENICKA*", "ana1@gmail.com"))));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        Boolean pr =SILab2.function(new User("ANA", "ANABANANA", "ana1@gmail.com"), createList(new User ("ana1@gmail.com", "LUBENICKA*", "ana1@gmail.com")));
        assertFalse(pr);

    }
}