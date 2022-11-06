package com.book.manager.infrastructure.database.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import com.book.manager.domain.model.Rental
import com.book.manager.domain.repository.BookRepository
import com.book.manager.infrastructure.database.mapper.BookMapper
import com.book.manager.infrastructure.database.mapper.custom.BookWithRentalMapper
import com.book.manager.infrastructure.database.mapper.custom.select
import com.book.manager.infrastructure.database.mapper.custom.selectByPrimaryKey
import com.book.manager.infrastructure.database.mapper.deleteByPrimaryKey
import com.book.manager.infrastructure.database.mapper.insert
import com.book.manager.infrastructure.database.mapper.updateByPrimaryKeySelective
import com.book.manager.infrastructure.database.record.BookRecord
import com.book.manager.infrastructure.database.record.custom.BookWithRentalRecord
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * BookRepositoryImpl Class
 * BookRepository(Interface)でBookRepositoryImplを実装し、DB関連実装をBookRepositoryImplに閉じ込める。
 * ORMの仕様に依存するBookWithRentalRecord(Class)ではなく、BookWithRental(Class)にConvertして返す。
 * -> 仮にORMやRDBに変更があっても、BookRepositoryImplだけ改修すれば済む。
 */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
) : BookRepository {
    /**
     * findAllWithRental() function
     * 1. execute BookWithRentalMapper.select() function
     *      1.1. execute toModel() function with it(BookWithRentalRecord)
     */
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map {
            // execute toModel() function with it(BookWithRentalRecord)
            toModel(it)
        }
    }

//    override fun findWithRental(id: Long): BookWithRental? {
//        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
//    }
//　
//    override fun register(book: Book) {
//        bookMapper.insert(toRecord(book))
//    }
//
//    override fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
//        bookMapper.updateByPrimaryKeySelective(BookRecord(id, title, author, releaseDate))
//    }
//
//    override fun delete(id: Long) {
//        bookMapper.deleteByPrimaryKey(id)
//    }

    /**
     * toModel() function
     * convert BookWithRentalRecordClass(Record) to BookWithRentalClass(Model)
     */
    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!
            )
        }
        return BookWithRental(book, rental)
    }

//    private fun toRecord(model: Book): BookRecord {
//        return BookRecord(model.id, model.title, model.author, model.releaseDate)
//    }
}