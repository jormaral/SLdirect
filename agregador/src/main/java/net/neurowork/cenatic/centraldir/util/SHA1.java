/*
 * Copyright 2010 CENATIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.neurowork.cenatic.centraldir.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 06/12/2010
 */
public class SHA1 {

    public static String getHash(String message) throws NoSuchAlgorithmException {
    	byte[] buffer, digest;
    	String hash = "";
    	MessageDigest md;
    	
        buffer = message.getBytes();
        md = MessageDigest.getInstance("SHA1");
        md.update(buffer);
        digest = md.digest();

        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }

        return hash;
    }
}