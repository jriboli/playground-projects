﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollQuickstart.StepDefinitions
{
    [Binding]
    public class PriceCalculationStepDefinitions
    {
        private readonly PriceCalculator _priceCalculator = new();
        private readonly Dictionary<string, int> _basket = new();
        private decimal _calculatedPrice;

        [Given("the client started shopping")]
        public void GivenTheClientStartedShopping()
        {
            _basket.Clear();
            _calculatedPrice = 0.0m;
        }

        [Given("the client added {int} pcs of {string} to the basket")]
        public void GivenTheClientAddedPcsOfToTheBasket(int quantity, string product)
        {
            _basket.Add(product, quantity);
        }

        [Given("the client added")]
        public void GivenTheClientAdded(DataTable itemsTable)
        {
            var items = itemsTable.CreateSet<(string Product, int Quantity)>();
            foreach (var item in items)
            {
                _basket.Add(item.Product, item.Quantity);
            }
        }

        [When("the basket is prepared")]
        public void WhenTheBasketIsPrepared()
        {
            _calculatedPrice = _priceCalculator.CalculatePrice(_basket);
        }

        [Then("the basket price should be ${float}")]
        public void ThenTheBasketPriceShouldBe(decimal expectedPrice)
        {
            Assert.AreEqual(expectedPrice, _calculatedPrice);
        }
    }
}
