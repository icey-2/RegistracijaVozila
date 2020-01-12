/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registracijaVozila.baza;

import java.sql.ResultSet;
import java.sql.Statement;
import registracijaVozila.data.Registracija;

/**
 *
 * @author Admin
 */
public class RegistracijaBaza {
    public static void insertRegistracija(Registracija registracija){
        Baza baza = new Baza();
        
        try{
            baza.poveziSaBazom();
            String chk = "Select * From Registracija Where registarska_oznaka='"+registracija.getRegistarskaOznaka()+"'";
            String insert = "UPDATE REGISTRACIJA SET DATUM_REGISTRACIJE=sysdate, REGISTROVAN_DO=add_months(sysdate,12) WHERE registarska_oznaka='"+registracija.getRegistarskaOznaka()+"' AND BROJ_SASIJE='"+registracija.getBrojSasije()+"'";
            String insert1 = "INSERT INTO Registracija (IME, PREZIME, JMBG, ADRESA, KONTAKT_TELEFON, REGISTARSKA_OZNAKA, MARKA, MODEL, BOJA, BROJ_SASIJE, GODINA_PROIZVODNJE, ZAPREMINA_MOTORA, SNAGA_MOTORA, DATUM_REGISTRACIJE, REGISTROVAN_DO) VALUES ("
                        +"'"+registracija.getIme()+"'"+", "
                        +"'"+registracija.getPrezime()+"'"+", "
                        +"'"+registracija.getJmbg()+"'"+", "
                        +"'"+registracija.getAdresa()+"'"+", "
                        +"'"+registracija.getKontakt()+"'"+", "
                        +"'"+registracija.getRegistarskaOznaka()+"'"+", "
                        +"'"+registracija.getMarka()+"'"+", "
                        +"'"+registracija.getModel()+"'"+", "
                        +"'"+registracija.getBoja()+"'"+", "
                        +"'"+registracija.getBrojSasije()+"'"+", "
                        +registracija.getGodiste()+", " 
                        +registracija.getZapreminaMotora()+", "  
                        +registracija.getSnaga()+", "
                        +"sysdate"+", "
                        +"add_months(sysdate,12)"+")";
            ResultSet rs=baza.naredba1.executeQuery(chk);
            
            if(rs.next()){
                
                baza.naredba1.executeUpdate(insert);
            }else{
                
                baza.naredba1.executeUpdate(insert1);         
            }
            
            
        }catch(Exception e){
            System.out.println("Greska: "+e);
        }finally{
            baza.zatvoriBazu();
        }
    }
}
