# Stock Tracker

A comprehensive stock tracking application built with Spring Boot that provides real-time stock data, company overviews, historical price information, and favorite stock management.

## Features

- **Real-time Stock Quotes**: Get current stock prices and trading information
- **Stock Overview**: Detailed company information including sector, industry, market cap, and financial ratios
- **Historical Data**: Access up to historical daily stock prices with customizable time periods
- **Favorites Management**: Save and manage your favorite stocks for quick access
- **Caching**: Intelligent caching system for improved performance
- **Exception Handling**: Comprehensive error handling with custom exceptions
- **H2 Database**: Lightweight embedded database for data persistence

<p align="center">
  <img src="https://github.com/user-attachments/assets/4b3cecaa-6e0b-42ff-8550-7972e8430fab" alt="Screenshot (37)" width="60%"/>
</p>


## Tech Stack

- **Framework**: Spring Boot 3.5.6
- **Language**: Java 21
- **Database**: H2 (embedded)
- **Web Client**: Spring WebFlux (reactive)
- **Data Access**: Spring Data JPA
- **Build Tool**: Maven
- **External API**: Alpha Vantage Stock API
- **Libraries**:
  - Lombok (for reducing boilerplate code)
  - Jackson (for JSON processing)

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Alpha Vantage API key (free registration required)

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/sathwikhbhat/stock-tracker.git
   cd stock-tracker
   ```

2. **Get Alpha Vantage API Key**
   - Register at [Alpha Vantage](https://www.alphavantage.co/support/#api-key)
   - Get your free API key

3. **Set Environment Variable**
   ```bash
   ALPHA_VANTAGE_API_KEY="your_api_key_here"
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Endpoints

### Stock Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/stocks/{symbol}` | Get current stock quote |
| GET | `/api/v1/stocks/{symbol}/overview` | Get detailed company overview |
| GET | `/api/v1/stocks/{symbol}/history?days={n}` | Get historical stock data (default: 30 days) |

### Favorites Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/stocks/favorites` | Add stock to favorites |
| GET | `/api/v1/stocks/favorites` | Get all favorite stocks |

## API Usage Examples

### Get Stock Quote
```bash
curl -X GET "http://localhost:8080/api/v1/stocks/AAPL"
```

**Response:**
```json
{
    "symbol": "AAPL",
    "price": 150.25,
    "lastUpdated": "2025-09-27"
}
```

### Get Stock Overview
```bash
curl -X GET "http://localhost:8080/api/v1/stocks/AAPL/overview"
```

### Get Stock History
```bash
curl -X GET "http://localhost:8080/api/v1/stocks/AAPL/history?days=7"
```

### Add to Favorites
```bash
curl -X POST "http://localhost:8080/api/v1/stocks/favorites" \
     -H "Content-Type: application/json" \
     -d '{"symbol": "AAPL"}'
```

### Get Favorites
```bash
curl -X GET "http://localhost:8080/api/v1/stocks/favorites"
```

## Database

The application uses H2 in-memory database for storing favorite stocks. 

- **Database URL**: `jdbc:h2:file:./data/stockdb`
- **H2 Console**: Available at `http://localhost:8080/h2-console`
- **Credentials**: 
  - Username: `sa`
  - Password: (empty)

## Key Features Explained

### Caching
- Implements Spring caching for stock data to reduce API calls
- Cache key is based on stock symbol

### Error Handling
- **StockNotFoundException**: When a stock symbol is not found
- **StockDataException**: When there's an issue fetching stock data
- **FavoriteAlreadyExistsException**: When trying to add a duplicate favorite
- Global exception handler for consistent error responses

### Data Models
- **StockResponse**: Current stock price and basic info
- **StockOverviewResponse**: Detailed company information
- **DailyStockResponse**: Historical daily stock data
- **FavoriteStock**: Entity for storing user's favorite stocks


## Configuration

Key configuration properties in `application.properties`:

```properties
# Application name
spring.application.name=Stock Tracker

# Alpha Vantage API
alpha.vantage.base.url=https://www.alphavantage.co/query
alpha.vantage.api.key=${ALPHA_VANTAGE_API_KEY}

# H2 Database
spring.datasource.url=jdbc:h2:file:./data/stockdb
spring.h2.console.enabled=true

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
