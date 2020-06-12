package com.giftcard.boundarytestcases;


import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.giftcard.dtos.GiftCardOrderDetailsDTO;
import com.giftcard.utilityclasses.MasterData;
@RunWith(JUnitPlatform.class)
public class EntityValidationTest {
	
	static{
		File file = new File("output_boundary_revised.txt");
		 if(file.exists()){
			 try {
				FileUtils.forceDelete(new File("output_boundary_revised.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		 }
	}

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testGiftOrdersSuccess() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
		File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestGiftOrdersSuccess="+(violations.isEmpty()?true:false), true); 
      
    }
    @Test
    public void testBuyerNameLength() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setBuyerFirstName("as");
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestBuyerNameLength="+(violations.isEmpty()?true:false), true); 
    }
    @Test
    public void testBuyerNameNull() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setBuyerFirstName(null);
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestBuyerNameNull="+(violations.isEmpty()?true:false), true); 
    }
    
    
    @Test
    public void testBuyerLastNull() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setBuyerLastName(null);
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestBuyerLastNull="+(violations.isEmpty()?true:false), true); 
    }
    
    @Test
    public void testPhoneNumberLegthExceed() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setPhoneNumber("901256899789");
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestPhoneNumberLegthExceed="+(violations.isEmpty()?true:false), true); 
    }
    
    @Test
    public void testPhoneNumberLegthCorrect() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setPhoneNumber("9999999999");
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestPhoneNumberLegthCorrect="+(violations.isEmpty()?true:false), true); 
    }
    @Test
    public void testPhoneNumberNull() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setPhoneNumber(null);
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestPhoneNumberNull="+(violations.isEmpty()?true:false), true);
    }
    
    @Test
    public void testGiftAmountNegative() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setGiftAmount(-100L);
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestGiftAmountNegative="+(violations.isEmpty()?true:false), true);
    }
    
    @Test
    public void testGiftAmountExceed() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setGiftAmount(500L);
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestGiftAmountExceed="+(violations.isEmpty()?true:false), true);
    }
    @Test
    public void testEmail() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setEmail("krishna");
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestEmail="+(violations.isEmpty()?true:false), true);
    }
    @Test
    public void testEmailvalidation1() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setEmail("krishna.com.com");
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestEmailvalidation1="+(violations.isEmpty()?true:false), true);
    }

    @Test
    public void testEmailvalidation2() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setEmail("krishna@gmail.com");
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestEmailvalidation2="+(violations.isEmpty()?true:false), true);
    }
    @Test
    public void testGiftAmount() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.setGiftAmount(100L);
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestGiftAmount="+(violations.isEmpty()?true:false), true);
    }
    
    @Test
    public void testShippingPincode() throws IOException {
    	GiftCardOrderDetailsDTO details = MasterData.getDetails();
    	details.getShippingId().setPincode("588888");
        Set<ConstraintViolation<GiftCardOrderDetailsDTO>> violations = validator.validate(details);
        File file = new File("output_boundary_revised.txt");
	    FileUtils.write(file, "\ntestShippingPincode="+(violations.isEmpty()?true:false), true);
    }
}