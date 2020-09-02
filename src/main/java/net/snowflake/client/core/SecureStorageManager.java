/*
 * Copyright (c) 2012-2020 Snowflake Computing Inc. All rights reserved.
 */

package net.snowflake.client.core;

/**
 * Interface for accessing Platform specific Local Secure Storage E.g. keychain on Mac credential
 * manager on Windows
 */
interface SecureStorageManager {
  String DRIVER_NAME = "SNOWFLAKE-JDBC-DRIVER";
  int COLON_CHAR_LENGTH = 1;

  SecureStorageStatus setCredential(String host, String user, String token);

  String getCredential(String host, String user);

  SecureStorageStatus deleteCredential(String host, String user);

  static String convertTarget(String host, String user) {
    StringBuilder target =
        new StringBuilder(
            host.length() + user.length() + DRIVER_NAME.length() + 2 * COLON_CHAR_LENGTH);

    target.append(host.toUpperCase());
    target.append(":");
    target.append(user.toUpperCase());
    target.append(":");
    target.append(DRIVER_NAME);

    return target.toString();
  }

  enum SecureStorageStatus {
    NOT_FOUND,
    FAILURE,
    SUCCESS,
    UNSUPPORTED
  }
}
