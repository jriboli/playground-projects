import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom'; // If you're using React Router for navigation

const CategoriesList = () => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    // Fetch categories from Strapi
    const fetchCategories = async () => {
      try {
        const response = await fetch('https://your-strapi-api-url/api/categories'); // Replace with your Strapi endpoint
        const categoriesData = await response.json;
        
        // Randomly shuffle the categories and pick the first 6
        const shuffledCategories = categoriesData.sort(() => 0.5 - Math.random()).slice(0, 6);
        setCategories(shuffledCategories);
      } catch (error) {
        console.error("Error fetching categories", error);
      }
    };

    fetchCategories();
  }, []);

  return (
    <div className="categories-list">
      <h3>Categories</h3>
      <div className="buttons">
        {categories.map((category) => (
          <Link
            key={category.id}
            to={`/blogs/category/${category.attributes.slug}`} // Assuming your categories have a 'slug' attribute
            className="category-button"
          >
            <button>{category.attributes.name}</button> {/* Assuming 'name' attribute */}
          </Link>
        ))}
      </div>
    </div>
  );
};

export default CategoriesList;