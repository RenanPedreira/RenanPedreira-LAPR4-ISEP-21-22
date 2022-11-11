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
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.cartmanagement.persistence.CartRepository;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.customermanagement.repositories.SignupRequestRepository;
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
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }
    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public ProductRepository products(final TransactionalContext autoTx) {
        return new JpaProductRepository(autoTx);
    }
    @Override
    public ProductRepository products() {
        return new JpaProductRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public CategoryRepository categories(final TransactionalContext autoTx) {
        return new JpaCategoryRepository(autoTx);
    }
    @Override
    public CategoryRepository categories() {
        return new JpaCategoryRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public RowRepository rows(TransactionalContext autoTx) {
        return new JpaRowRepository(autoTx);
    }

    @Override
    public RowRepository rows() {
        return new JpaRowRepository(Application.settings().getPersistenceUnitName(),Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public AgvDockRepository agvDocks(TransactionalContext autoTx) {
        return new JpaAgvDockRepository(autoTx);
    }

    @Override
    public AgvDockRepository agvDocks() {
        return new JpaAgvDockRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public AssignmentRepository assignments(TransactionalContext autoTx) {
        return new JpaAssignmentRepository(autoTx);
    }

    @Override
    public AssignmentRepository assignments() {
        return new JpaAssignmentRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public CartRepository cartRepository(TransactionalContext autoTx) {
        return new JpaCartRepository(autoTx);
    }

    @Override
    public CartRepository cartRepository() {
        return new JpaCartRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public WarehouseRepository warehouses(TransactionalContext autoTx) {
        return new JpaWarehouseRepository(autoTx);
    }
    @Override
    public WarehouseRepository warehouses() {
        return new JpaWarehouseRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public AisleRepository aisles(TransactionalContext autoTx) {
        return new JpaAisleRepository(autoTx);
    }

    @Override
    public AisleRepository aisles() {
        return new JpaAisleRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public OrderRepository orders(TransactionalContext autoTx) {
        return new JpaOrderRepository(autoTx);
    }

    @Override
    public OrderRepository orders() {
        return new JpaOrderRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
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
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }
    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }
    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CustomerRepository customerRepository(TransactionalContext context) {
        return new JpaCustomerRepository(context);
    }

    @Override
    public CustomerRepository customerRepository() {
        return new JpaCustomerRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AGVRepository agvRepository(TransactionalContext context) {
        return new JpaAGVRepository(context);
    }

    @Override
    public AGVRepository agvRepository() {
        return new JpaAGVRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public QuestionnaireRepository questionnaireRepository(TransactionalContext context) {
        return new JpaQuestionnaireRepository(context);
    }

    @Override
    public QuestionnaireRepository questionnaireRepository() {
        return new JpaQuestionnaireRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public StatisticalReportRepository statisticalReportRepository(TransactionalContext context) {
        return new JPAStatisticalRepository(context);
    }

    @Override
    public StatisticalReportRepository statisticalReportRepository() {
        return new JPAStatisticalRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

}
