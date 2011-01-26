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

import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.springframework.util.StringUtils;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 17/11/2010
 */
public class CategoryDatasetCreator implements GraphDatasetCreator {

	@Override
	public Dataset createDataset(Map<String, ? extends Number> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		if(data == null)
			return dataset;
		
		for(String key : data.keySet()){
			Number number = data.get(key);

			if(key == null || !StringUtils.hasLength(key.toString())){
				key = "Sin Categoría";
			}
			dataset.addValue(number, "", key);
			
//			Long count = null;
//			if(((Object[])o)[0] instanceof Long){
//				count = ((Long)((Object[])o)[0]);
//			}else if(((Object[])o)[0] instanceof Integer){
//				count = ((Integer)((Object[])o)[0]).longValue();
//			}
//			Object obj = ((Object[])o)[1];
//			if(obj == null || !StringUtils.hasLength(obj.toString())){
//				obj = "Sin Categoría";
//			}
//			if(count != null){
//				String value = obj.toString();
//				dataset.addValue(count, "", value);
//			}
		}
		
		return dataset;
	}



}
