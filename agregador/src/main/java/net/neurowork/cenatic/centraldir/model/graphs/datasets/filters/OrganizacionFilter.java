package net.neurowork.cenatic.centraldir.model.graphs.datasets.filters;

import java.util.Collection;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public interface OrganizacionFilter {
	Collection<Organizacion> filter(Collection<Organizacion> organizacions);
}
