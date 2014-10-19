package service.report;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by adelfiri on 05.10.14.
 */
@Service
public class TokenService {
    private HashMap<String,String> tokens = new HashMap<String, String>();

    public String check(String token) {
        return tokens.get(token);
    }

    public String generate() {
        String uid = UUID.randomUUID().toString();
        tokens.put(uid, uid);
        return uid;
    }

    public void remove(String token) {
        tokens.remove(token);
    }
}
