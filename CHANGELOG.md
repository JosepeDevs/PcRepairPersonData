# Changelog

All notable changes to this project will be documented in this file.  
The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/)  
and adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Unreleased

## [0.3.0] - 2025-11-05

## Added

- Added sonarQube docker-compose and instructions for use in readme

## Changed

- Delete and FindById take into account if is already deleted (soft)

## Fixed

- Update now applies the provided data instead of only re-activating the user.

## [0.2.0] - 2025-11-04

## Added

- Soft delete and differentiated with hard delete.  
- Used spotless
- Added mapstruct usage

## Changed

- LOBs are now charged using separate files
- UUIDs replaced with Strings, to allow creation of Id = UUID+timestamp (impossible collision)
- refactors to more purist use of hexagonal architecture: use functional interfaces, improve names
- GlobalExceptionHandler now returns chosen HttpStatus, instead of a fixed one.
- Added deleted to mappers and dtos.

## Removed

- Useless/explicative comments (used for learning, they did not add value to the code)

## Fixed

- All DTO have been reviewed (name and fields) for better understanding of their scope.

## [0.1.1] - 2025-11-03

## Added

- Added docker support and oracle database profile


## [Previous Versions]

For details prior to 0.0.1, refer to commit history.