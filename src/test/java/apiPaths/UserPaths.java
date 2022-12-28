package apiPaths;
/**
 * <h1>Add a ping!</h1> The APITest holds all paths of an API module that is store all URL extension
 * 
 * @author Joe Phan
 * @since 2022 Oct 14th
 */

public class UserPaths {
	// GET
	public static final String GET_LIST_OF_USERS = "/api/users?page=1";
	public static final String GET_USER_NOT_FOUND = "/api/users/567";
	public static final String GET_VALID_USER= "/api/users/3";
	public static final String UPDATE_USER= "/api/users/3";
	
	// POST
		public static final String CREATE_A_USER = "/api/users";
	
}
