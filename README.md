# InventoryManagement
# Inventory Management System

## Overview

The Inventory Management System is a Java-based application designed to streamline the management of parts and products within an organization. This application provides features to add, modify, and organize parts and products, ensuring smooth inventory operations. Built with an intuitive user interface, the system allows users to manage and control their inventory efficiently, offering a seamless experience from part creation to product association.

## Key Features

- **Add Part and Product**: Easily create new parts and products to expand the inventory.
- **Modify Existing Records**: Update information of existing parts or products to reflect changes in inventory.
- **Inventory Overview**: View all parts and products in a single, cohesive main screen.
- **Categorize Parts**: Manage parts through classifications such as in-house manufacturing or outsourced production.

## Class Descriptions

The system is built with a clean and modular structure, leveraging multiple classes for distinct functionality, which ensures maintainability and scalability.

### Classes

#### 1. **AddPart**

Handles the Add Part form, enabling users to add new parts to the inventory. It provides an intuitive interface for inputting all required part details.

#### 2. **AddProduct**

Handles the Add Product form, allowing users to add new products and associate them with relevant parts.

#### 3. **HelloApplication**

The entry point of the application. Extends the `Application` class and initializes the user interface for the entire project.

#### 4. **InHouse**

A subclass of the `Part` class that represents parts manufactured in-house, providing specific attributes required for such parts.

#### 5. **Inventory**

Represents the inventory of parts and products. This class is responsible for managing the collection of all parts and products, providing methods for adding, removing, and searching inventory items.

#### 6. **MainScreenController**

Acts as the controller for the main view of the application. It facilitates user interactions on the main dashboard, allowing users to access inventory details and perform CRUD (Create, Read, Update, Delete) operations.

#### 7. **ModifyPart**

Handles the Modify Part form, responsible for managing the user interface for modifying existing parts within the inventory. It ensures the integrity and consistency of part data.

#### 8. **ModifyProduct**

Controller for the "Modify Product" screen, enabling users to make changes to existing products and manage associated parts.

#### 9. **Outsourced**

A subclass of the `Part` class that represents parts that are outsourced. This class includes attributes necessary to track vendor information.

#### 10. **Part**

Abstract base class for parts in the inventory. This class provides shared properties and methods for both `InHouse` and `Outsourced` parts.

#### 11. **Product**

Represents a product within the inventory. This class contains information about the product itself and manages the list of parts associated with the product.

## Getting Started

### Prerequisites

- Java 8 or higher
- IDE such as IntelliJ IDEA or Eclipse

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/inventory-management-system.git
   ```
2. Open the project in your preferred IDE.
3. Build and run the `HelloApplication` class to start the application.

### Usage

- **Add a Part**: Navigate to the "Add Part" form to create a new part. Fill in the necessary fields and save the part to inventory.
- **Add a Product**: Use the "Add Product" form to create a new product. Associate relevant parts to complete product creation.
- **Modify Entries**: Select an existing part or product from the main screen and click "Modify" to edit its details.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

##

