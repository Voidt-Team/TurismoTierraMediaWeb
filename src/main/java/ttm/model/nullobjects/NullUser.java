package ttm.model.nullobjects;

import ttm.model.Usuario;

public class NullUser extends Usuario {

	public static User build() {
		return new NullUser();
	}
	
	public NullUser() {
		super(0, "", "", 0, 0.0, false);
	}
	
	public boolean isNull() {
		return true;
	}
	
}
