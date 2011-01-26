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
package net.neurowork.cenatic.centraldir.model.graphs.datasets;

import java.security.InvalidParameterException;
import java.util.Map;

import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.util.StringUtils;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 17/11/2010
 */
public class PieDatasetCreator implements GraphDatasetCreator {
	public Dataset createDataset(Map<String, ? extends Number> data) {
		if(data == null)
			throw new InvalidParameterException("null values");
		
		DefaultPieDataset ret = new DefaultPieDataset();
		
		for(String key : data.keySet()){
			Number number = data.get(key);

			if(key == null || !StringUtils.hasLength(key.toString())){
				key = "Sin Categoría";
			}
			ret.setValue(key, number);
//			
//			
//			Integer count = (Integer)((Object[])o)[0];
//			Object obj = ((Object[])o)[1];
//			if(count != null && obj != null){
//				String value = obj.toString();
//				ret.setValue(value, count);
//			}
		}
		return ret;
	}

}
