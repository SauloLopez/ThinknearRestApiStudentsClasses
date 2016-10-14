package com.api.utils;

public class ApiException extends Exception
{
	private static final long serialVersionUID = -740524720644291276L;

	public ApiException(String message)
      {
         super(message);
      }
 }
