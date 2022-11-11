/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.cartmanagement.persistence.CartRepository;
import eapli.base.customermanagement.repositories.ClientUserRepository;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.customermanagement.repositories.SignupRequestRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.ordermanagement.persistence.OrderItemRepository;
import eapli.base.ordermanagement.persistence.OrderRepository;
import eapli.base.productmanagement.persistence.CategoryRepository;
import eapli.base.productmanagement.persistence.ProductRepository;
import eapli.base.questionnairemanagement.persistence.QuestionnaireRepository;
import eapli.base.statistics.persistence.StatisticalReportRepository;
import eapli.base.warehousemanagement.repository.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ProductRepository products(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public ProductRepository products() {
        return null;
    }

    @Override
    public CategoryRepository categories(final TransactionalContext tx) {
        return null;
    }

    @Override
    public CategoryRepository categories() {
        return null;
    }

    @Override
    public RowRepository rows(TransactionalContext autoTx) {
        return new inMemoryRowRepository();
    }

    @Override
    public RowRepository rows() {
        return null;
    }

    @Override
    public AgvDockRepository agvDocks(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public AgvDockRepository agvDocks() {
        return null;
    }

    @Override
    public AssignmentRepository assignments(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public AssignmentRepository assignments() {
        return null;
    }

    @Override
    public CartRepository cartRepository(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public CartRepository cartRepository() {
        return null;
    }

    @Override
    public WarehouseRepository warehouses(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public WarehouseRepository warehouses() {
        return null;
    }

    @Override
    public AisleRepository aisles(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public AisleRepository aisles() {
        return null;
    }

    @Override
    public OrderRepository orders(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public OrderRepository orders() {
        return null;
    }

    @Override
    public OrderItemRepository orderItems(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public OrderItemRepository orderItems() {
        return null;
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {
        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public CustomerRepository customerRepository(TransactionalContext context) {
        return new InMemoryCustomerRepository();
    }

    @Override
    public CustomerRepository customerRepository() {
        return customerRepository(null);
    }

    @Override
    public AGVRepository agvRepository(TransactionalContext context) {
        return null;
    }

    @Override
    public AGVRepository agvRepository() { return null; }

    @Override
    public QuestionnaireRepository questionnaireRepository(TransactionalContext context) {
        return null;
    }

    @Override
    public QuestionnaireRepository questionnaireRepository() {
        return null;
    }

    @Override
    public StatisticalReportRepository statisticalReportRepository(TransactionalContext context) {
        return null;
    }

    @Override
    public StatisticalReportRepository statisticalReportRepository() {
        return null;
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
