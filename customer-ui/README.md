# Customer Management UI

A React-based user interface for managing customer information, built with Material-UI components.

## Features

- View a list of all customers in a responsive table format
- Display customer details including:
  - Customer ID
  - First Name
  - Last Name
  - Email
  - Date of Birth
- Error handling with user-friendly error messages
- Clean and modern UI using Material-UI components

## Technical Stack

- React.js
- Material-UI (MUI)
- Axios (via customerService)

## Getting Started

### Prerequisites

- Node.js (version 14 or higher)
- npm or yarn package manager

### Installation

1. Clone the repository:
```bash
git clone [repository-url]
cd customer-ui
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

## Project Structure

```
src/
  ├── components/
  │   └── ViewCustomers.js   # Main component for displaying customer list
  ├── services/
  │   └── customerService.js # API service for customer data
  └── ...
```

## API Integration

The application integrates with a backend service through `customerService.js`, which handles all customer-related API calls.

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.