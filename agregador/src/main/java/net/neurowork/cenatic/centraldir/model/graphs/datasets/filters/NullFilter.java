/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.graphs.datasets.filters;

import java.util.Collection;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

/**
 * Do not filter
 */
public class NullFilter implements OrganizacionFilter {

	@Override
	public Collection<Organizacion> filter(Collection<Organizacion> organizacions) {
		return organizacions;
	}

}
