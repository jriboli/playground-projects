package Week16;

public class Week16_Research {
	/*
	 * 1. When discussing handling of sensitive data, how do hashing and salting work? Why would we need to hash and salt certain data?
	 * 
	 * ANSWER:
	 * When handling sensitive data, hashing and salting are cryptographic techniques employed to enhance security. Hashing involves transforming 
	 * sensitive information, like passwords, into fixed-length strings using a one-way hash function. This irreversible process ensures that 
	 * even if the hashed value is compromised, the original data cannot be easily retrieved. Salting adds an extra layer of security by 
	 * appending a unique, random value (salt) to the data before hashing. This ensures that identical passwords result in different hashes, 
	 * making it challenging for attackers using precomputed tables (rainbow tables). Hashing and salting are crucial for protecting sensitive 
	 * information, particularly passwords, as they significantly mitigate the risk of unauthorized access, even in the event of a data breach.
	 * 
	 * 2. What are the three parts of a JSON Web Token (JWT)?
	 * 
	 * ANSWER:
	 * A JWT is a string with three parts separated by dots: header, payload, and signature. These parts are Base64Url-encoded 
	 * and concatenated to form the complete token.
	 * 
	 * 3. If a JWT can be decoded by anyone, how are they useful? What problem do they solve and how do they solve it securely?
	 * 
	 * ANSWER:
	 * While JWTs can be decoded by anyone, their security is upheld by digital signatures (or encryption) that verify the integrity 
	 * and authenticity of the token. This makes JWTs a secure solution for transmitting information between parties while maintaining 
	 * the advantages of compactness and stateless authentication.
	 * 
	 * 4. Research Spring Security.   Though not covered in this course, how to provide security is a question that many of our students have.
	 *   Take a look into some of the available resources to provide security to your application.
	 *   
	 * ANSWER:
	 * Spring Security offers a range of options for securing applications, and it provides comprehensive solutions for authentication, authorization, 
	 * and various security features. Here are some key components and features offered by Spring Security:
	 * 
	 * 1. Authentication Providers
	 * 2. Authoriation
	 * 3. Token-Based Authentication
	 * 4. Web Security
	 * 5. Secutiry Configuration
	 * 6. LDAP Integration
	 * 7. Event Handling
	 * 8. Remember-Me Authentication
	 * 9. Security Annotations
	 * 10. OAuth 2.0 Resource Server
	 * 
	 */
}
