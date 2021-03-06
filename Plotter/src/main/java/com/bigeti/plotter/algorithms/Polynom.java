package com.bigeti.plotter.algorithms;

import com.bigeti.plotter.core.IAlgorithm;

/**
 * Polynom class
 *
 * @author Ethem Kurt
 * @version 1.0.0
 * @since 1.0.0
 */
public class Polynom implements IAlgorithm<Double, Double>
{

	/**
	 * Factors
	 */
	private final Double[] factors;

	/**
	 * Constructor
	 *
	 * @param factors
	 *            Factors
	 */
	public Polynom(final double[] factors)
	{
		if (factors == null)
		{
			throw new IllegalArgumentException("\"factors\" can't be \"null\".");
		}
		if (factors.length <= 0)
		{
			throw new IllegalArgumentException("\"factors\" can't be zero by its length.");
		}

		this.factors = new Double[factors.length];
		for (int i = 0; i < factors.length; i++)
		{
			this.factors[i] = factors[i];
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.plotter.core.IAlgorithm#compute(java.lang.Object)
	 */
	@Override
	public Double compute(final Double value)
	{
		double ret = 0.0;
		for (int i = 0; i < factors.length; i++)
		{
			ret += factors[i] * Math.pow(value, i);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("y = ");
		for (int i = 0; i < factors.length; i++)
		{
			if (i > 0)
			{
				sb.append(" + ");
				sb.append(factors[i]);
				if (i == 1)
				{
					sb.append("x");
				}
				else
				{
					sb.append("x^");
					sb.append(i);
				}
			}
			else
			{
				sb.append(factors[i]);
			}
		}
		return sb.toString();
	}

}
