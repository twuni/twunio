package io.twun.util;

public class Base64 {

	public static byte [] decode( byte [] input ) {

		int inputSize = input.length;
		int inputOffset = 0;
		int outputOffset = 0;
		byte [] block = new byte [4];
		int i;
		byte currentValue;

		int outputSize = (int) Math.ceil( input.length / 4.0 ) * 3;
		byte [] output = new byte [outputSize];

		while( inputOffset < inputSize ) {

			for( i = 0; i < 4; i++ ) {
				currentValue = input[inputOffset + i];
				if( currentValue == PADDING ) {
					block[i] = 0;
					outputSize--;
				} else {
					block[i] = (byte) indexOf( ALPHABET, currentValue );
				}
			}

			inputOffset += 4;

			output[outputOffset++] = (byte) ( block[0] << 2 | block[1] >> 4 );
			output[outputOffset++] = (byte) ( block[1] << 4 | block[2] >> 2 );
			output[outputOffset++] = (byte) ( block[2] << 6 | block[3] >> 0 );

		}

		if( outputSize < output.length ) {
			byte [] temp = new byte [outputSize];
			System.arraycopy( output, 0, temp, 0, outputSize );
			return temp;
		}

		return output;

	}

	public static byte [] encode( byte [] input ) {

		int inputLength = input.length;
		int outputLength = (int) Math.ceil( input.length / 3.0 ) * 4;

		byte [] block = new byte [3];
		int blockSize = 0;

		byte [] output = new byte [outputLength];

		int inputOffset = 0;
		int outputOffset = 0;

		while( inputOffset < inputLength ) {

			for( int i = 0; i < 3; i++ ) {
				if( inputOffset + i >= inputLength ) {
					block[i] = 0;
				} else {
					block[i] = input[inputOffset + i];
					blockSize = i + 1;
				}
			}

			inputOffset += 3;

			output[outputOffset++] = ALPHABET[block[0] >> 2];
			output[outputOffset++] = ALPHABET[( block[0] & 0x03 ) << 4 | ( block[1] & 0xf0 ) >> 4];
			output[outputOffset++] = blockSize > 1 ? ALPHABET[( block[1] & 0x0f ) << 2 | ( block[2] & 0xc0 ) >> 6] : PADDING;
			output[outputOffset++] = blockSize > 2 ? ALPHABET[block[2] & 0x3f] : PADDING;

		}

		return output;

	}

	private static int indexOf( byte [] array, byte value ) {
		for( int i = 0; i < array.length; i++ ) {
			if( array[i] == value ) {
				return i;
			}
		}
		return -1;
	}

	private static byte PADDING = '=';

	private static byte [] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".getBytes();

}
