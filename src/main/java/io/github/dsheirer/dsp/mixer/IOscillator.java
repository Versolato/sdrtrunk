/*******************************************************************************
 * sdr-trunk
 * Copyright (C) 2014-2018 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by  the Free Software Foundation, either version 3 of the License, or  (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied
 * warranty of  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License  along with this program.
 * If not, see <http://www.gnu.org/licenses/>
 *
 ******************************************************************************/
package io.github.dsheirer.dsp.mixer;

import io.github.dsheirer.sample.buffer.ReusableBuffer;
import io.github.dsheirer.sample.buffer.ReusableComplexBuffer;

/**
 * Oscillator interface.
 */
public interface IOscillator
{
    /**
     * Frequency of the tone being generated by the oscillator
     * @return frequency in hertz
     */
    double getFrequency();

    /**
     * Sets the frequency of the tone being generated by the oscillator.
     * @param frequency in hertz
     */
    void setFrequency(double frequency);

    /**
     * Sample rate of the oscillator
     * @return sample rate in hertz
     */
    double getSampleRate();

    /**
     * Sets the sample rate of the oscillator
     * @param sampleRate in hertz
     */
    void setSampleRate(double sampleRate);


    /**
     * Indicates if this oscillator is enabled, meaning that it has a non-zero frequency value.
     * @return true if the frequency value is non-zero
     */
    boolean isEnabled();

    /**
     * Current quadrature/imaginary value of the oscillator.  Note: if you are using this value iteratively, make sure
     * you invoke rotate() after each time you invoke this method.
     * @return current quadrature value of the oscillator.
     */
    float quadrature();

    /**
     * Current inphase/real value of the oscillator.  Note: if you are using this value iteratively, make sure you
     * invoke rotate() after each time you invoke this method.
     * @return current inphase value of the oscillator.
     */
    float inphase();


    /**
     * Rotates the oscillator by one sample period to generate new inphase() and quadrature() values.
     */
    void rotate();

    /**
     * Generates the specified number of real samples into a sample array.
     * @param sampleCount number of samples to generate and length of the resulting float array.
     * @return generated samples
     */
    float[] generateReal(int sampleCount);


    /**
     * Generates the specified number of complex samples where the samples are arranged as i0,q0,i1,q1 ... iN-1,qN-1
     * @param sampleCount number of samples to generate.
     * @return generated complex samples with an array length of 2 * N
     */
    float[] generateComplex(int sampleCount);


    /**
     * Generates complex samples and fills the reusable complex buffer using the current system time in millis for
     * the update timestamp.
     *
     * @param reusableComplexBuffer to fill with complex samples
     */
    void generateComplex(ReusableComplexBuffer reusableComplexBuffer);

    /**
     * Generates real samples and fills the reusable buffer using the current system time in millis for
     * the update timestamp.
     *
     * @param reusableBuffer to fill with complex samples
     */
    void generateReal(ReusableBuffer reusableBuffer);

    /**
     * Mixes (heterodynes) the complex sample array using the current settings of this oscillator.
     * @param complexSamples to mix to a new frequency
     * @return mixed/heterdyned samples
     */
    float[] mixComplex(float[] complexSamples);
}
